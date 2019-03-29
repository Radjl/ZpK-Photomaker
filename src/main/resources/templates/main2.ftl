<!DOCTYPE html>
<html lang="en">
<#include "parts/main2.ftl">
<body>



<nav class="main-navigation">
    <div class="navbar-header animated fadeInUp">
        <a class="navbar-brand" href="#">    <img class="mb-4" src="/static/img/logo.png">
        </a>

    </div>
    <ul class="nav-list">
            <li class="nav-list-item">
                <a href="/admin" class="nav-link">Панель администратора</a>
            </li>
        <li class="nav-list-item">

        </li>
        <li class="nav-list-item">
        </li>
    </ul>

</nav>



<div class="container">
    <div class="row">
        <div class="col-lg-12 my-3">
            <div class="pull-right">
                <div class="btn-group">
                    <button class="btn btn-info" id="list">
                       Отобразить списком
                    </button>
                    <button class="btn btn-danger" id="grid">
                        Отобразить сеткой
                    </button>
                    <form method="post" action="/delete">
                        <button type="submit" class="btn btn-danger" id="grid">Del</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="products" class="row view-group">
        <div class="item col-xs-4 col-lg-4">
            <#list carriagemassive as podacha>
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

<script>
    $(document).ready(function() {
        $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
        $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
    });
</script>
<#include "parts/scripts.ftl">
</body>
</html>