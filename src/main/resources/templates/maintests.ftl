<!DOCTYPE html>
<html lang="en">
<#include "parts/test.ftl">



<body>



<nav class="main-navigation">
    <div class="navbar-header animated fadeInUp">
        <a class="navbar-brand" href="#">    <img class="mb-4" src="/static/img/logo.png">
        </a>

    </div>
    <ul class="nav-list">
            <li class="nav-list-item ">
             <#if (cariage?size > 1000)>   <a href="/ssetings"   class="nav-link">Настройки</a></#if>
            </li>
        <li class="nav-list-item">

        </li>
        <li class="nav-list-item">
           <form action="/filter" method="post" name="DataForm">
            <input id="datepicker" name="date" placeholder="Выбрать по дате..." width="276" />
           </form>
        </li>
    </ul>
</nav>










<#if (cariage?size > 0) >





    <h2 class="text-center text-danger font-weight-bold">Идёт подача вагонов...</h2>

    <#list cariage  as podacha>
    <div class="container ">

        <div class="carousel slide article-slide" id="${podacha.id}" data-ride="carousel">




            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img  class="img-fluid bg-overlay">
                </div>

        <#list podacha.photos as photo>
                    <div class="carousel-item">
                        <img src="/img/ship/${photo}" class="img-fluid">

                    </div>
            </#list>



            </div>
            <ol class="carousel-indicators">

                <li data-target="$#{podacha.id}" data-slide-to="0" class="active">
                    <img alt="" src="http://placehold.it/250x180">
                </li>
                <#list podacha.photos as photo>

                    <li data-target="#${podacha.id}" data-slide-to="${photo_index + 1}">
                        <img alt="" src="/img/ship/${photo}">
                    </li>
                </#list>
            </ol>
            <a href="#${podacha.id}" class="carousel-control-prev" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a href="#${podacha.id}" class="carousel-control-next" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>


    </div>
    </#list>


<#else >
    <#if lastcreated??>
        <h2 class="text-center font-weight-bold">Подачи вагонов за сутки</h2>
        <#else>
            <h2 class="text-center font-weight-bold">За текущие сутки подач нет.</h2>
    </#if>
<#list carriagemassive2 as podacha>
<div class="container ">

    <div class="carousel slide article-slide" id="${podacha.id}" data-ride="carousel">




        <div class="carousel-inner">
            <div class="carousel-item active">
                <img  class="img-fluid bg-overlay">
                <div class="carousel-caption">
                    <h1>Дата подачи: ${podacha.getStartTime().getDayOfMonth()}-${podacha.getStartTime().getMonth().getValue()}-${podacha.getStartTime().getYear()?c}  </h1>
                    <p> <h1>Время подачи: ${podacha.getStartTime().getHour()} часов ${podacha.getStartTime().getMinute()} минут</h1> </p>
                    <p> <h1>Количество вагонов: ${podacha.getPhotos()?size -1}</h1> </p>
                    <p><a class="btn btn-sm btn-dark" href="#" role="button">Удалить запись</a></p>
                </div>
            </div>

            <#list podacha.photos as photo>
            <div class="carousel-item">
                <img src="/img/ship/${photo}" class="img-fluid">

            </div>

            </#list>


        </div>
        <ol class="carousel-indicators">

            <li data-target="$#{podacha.id}" data-slide-to="0" class="active">
                <img alt="" src="http://placehold.it/250x180">
            </li>
            <#list podacha.photos as photo>

                <li data-target="#${podacha.id}" data-slide-to="${photo_index + 1}">
                    <img alt="" src="/img/ship/${photo}">
                </li>
            </#list>
        </ol>
        <a href="#${podacha.id}" class="carousel-control-prev" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a href="#${podacha.id}" class="carousel-control-next" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>


</div>
</#list>
</#if>

<p></p>
<p></p>
<p></p>
<p></p>









<script>
    var $datepicker = $('#datepicker').datepicker({ footer: true, modal: true, header: true, uiLibrary: 'bootstrap4',locale: 'ru-ru',
          change: function (e) {
            document.forms["DataForm"].submit();
        } });
</script>


<script>
    $('.carousel').carousel({
        interval: 5000000
    })

</script>







</body>
</html>