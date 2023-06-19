package com.example.kfu_app.presentation.di

import com.example.kfu_app.presentation.authorization.AuthActivity
import com.example.kfu_app.presentation.authorization.signin.SignInFragment
import com.example.kfu_app.presentation.authorization.signup.SignUpFragment
import com.example.kfu_app.presentation.common.AppActivity
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesFragment
import com.example.kfu_app.presentation.fragments.entering.EnteringFragment
import com.example.kfu_app.presentation.fragments.main.MainFragment
import com.example.kfu_app.presentation.fragments.navigationDrawer.schedule.ScheduleFragment
import com.example.kfu_app.presentation.fragments.navigationDrawer.statements.StatementsFragment
import com.example.kfu_app.presentation.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    fun inject(activity: AppActivity)

    fun inject(activity: AuthActivity)
    fun inject(signInFragment: SignInFragment)

    fun inject(singUpFragment: SignUpFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(mainFragment: MainFragment)

    fun inject(fragment: InstitutesFragment)

    fun inject(fragment: EnteringFragment)

    fun inject(fragment: StatementsFragment)

    fun inject(fragment: ScheduleFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ScreenComponent
    }
}