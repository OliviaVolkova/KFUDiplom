package com.example.kfu_app.presentation.fragments.navigationDrawer.schedule

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.data.repositories.FirebaseLessonRepository
import com.example.kfu_app.databinding.FragmentScheduleBinding
import com.example.kfu_app.presentation.news.NewsAdapter
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    lateinit var lessonAdapter: LessonAdapter

    @Inject
    lateinit var firebaseLessonsRepository: FirebaseLessonRepository

    lateinit var datePicker: DatePicker
    lateinit var button: Button
    lateinit var rvLessons: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        datePicker = binding.datePicker
        rvLessons = binding.rvLessons
        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)

        val dayOfWeek = c.get(Calendar.DAY_OF_WEEK)

        lifecycleScope.launch {
            val lessonList = firebaseLessonsRepository.getLessonsByDayOfWeek(dayOfWeek)
            lessonAdapter.submitList(lessonList)
        }

        datePicker.init(year, month, day, null);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lessonLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvLessons.layoutManager = lessonLayoutManager
        lessonAdapter = LessonAdapter(requireContext()).also {
            rvLessons.adapter = it
        }

        datePicker.setOnDateChangedListener { _, year, month, day ->
            val c: Calendar = Calendar.getInstance()
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, day)
            val dayOfWeek = c.get(Calendar.DAY_OF_WEEK)

            lifecycleScope.launch {
                val lessonList = firebaseLessonsRepository.getLessonsByDayOfWeek(dayOfWeek)
                lessonAdapter.submitList(lessonList).also {
                    requireContext().clickSound()
                }
            }

        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }
    }


}
