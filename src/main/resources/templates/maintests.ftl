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
            <li class="nav-list-item">
                <a href="/ssetings" class="nav-link">Настройки</a>
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



    <div class="container">
        <div class="row">
            <div class="col-lg-12 my-3">
                <div class="pull-right">
                    <div class="btn-group">

                        <form method="post" action="/delete">
                            <button type="submit" class="btn btn-danger" id="grid">Del</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="products" class="row view-group">
            <div class="item col-xs-4 col-lg-4">
                <#list cariage as podacha>
                    <div class="thumbnail card">
                        <#if podacha.photos??>
                            <div class="img-event">
                                <#list podacha.photos as photo><img class="group list-group-image img-fluid" src="/img/ship/${photo}" alt="" /></#list>
                            </div>
                        </#if>
                        <div class="caption card-body">
                            <h4 class="group card-title inner list-group-item-heading">
                            </h4>
                            <p class="group inner list-group-item-text">
                            </p>
                            <div class="row">
                                <div class="col-xs-12 col-md-6">
                                    <p class="lead">    </p>
                                </div>
                                <div class="col-xs-12 col-md-6">

                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>


        </div>
    </div>


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


<#include "parts/scripts.ftl">


</body>
</html>