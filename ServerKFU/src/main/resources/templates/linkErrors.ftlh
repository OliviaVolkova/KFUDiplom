<!doctype html>
<html lang="ru">
<head>
    <!-- Кодировка веб-страницы -->
    <meta charset="utf-8">
    <!-- Настройка viewport -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>...</title>

    <!-- Bootstrap CSS (jsDelivr CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap Bundle JS (jsDelivr CDN) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <style>

        .dropdown-toggle {
            background-color: #004066;
            color: white;
            border-radius: 15px;
            width: 150px;
            height: 45px;
            margin-top: 50px;
            margin-left: 30px;
            margin-bottom: 20px;
        }

        #dropdownMenuButton {
            background-color: #004066;
            color: white;
        }

        .ui-menu-item-wrapper {
            background-color: #EBEFFBE5;
            width: auto;
            height: 80px;
            margin-top: 5px;
            padding-left: 50px;
            padding-top: 10px;
        }

        .form-switch.form-switch-xl {
            margin-bottom: 2rem; /* JUST FOR STYLING PURPOSE */
        }

        .form-switch.form-switch-xl .form-check-input {
            height: 2.5rem;
            width: calc(4rem + 0.75rem);
            border-radius: 5rem;
            background-color: #004066;
            color: #EBEFFBE5;
            position: absolute;
            right: 20px;
            bottom: 15px;
        }

        #flexSwitchCheckChecked {

        }


    </style>

</head>
<form action="${springMacroRequestContext.contextPath}/linkError" method="POST">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" aria-label="dropdown-menu">
            VERSION ${version}
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="${springMacroRequestContext.contextPath}/linkError?version=1.1">VERSION 1.1</a>
            <a class="dropdown-item" href="${springMacroRequestContext.contextPath}/linkError?version=1.2">VERSION 1.2</a>
            <a class="dropdown-item" href="${springMacroRequestContext.contextPath}/linkError?version=1.3">VERSION 1.3</a>
        </div>
    </div>

    <#if linkErrors??>
        <#list linkErrors as linkError>
            <div class="ui-menu-item-wrapper">
                <p>${linkError.link}</p>
                <div class="form-check form-switch form-switch-xl">
                    Search: <input type=${linkError.count} name="searchText"/>
                </div>
            </div>
        </#list>
    </#if>

    <div class="submit-button">
        <input type="submit" value="Save" name="color-button" class="btn btn-dark"/>
    </div>
</form>
</body>
</html>