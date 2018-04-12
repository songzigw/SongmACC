<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" session="false" isELIgnored="false"%>
<script type="text/x-template" id="songm-user-base-template">
    <div class="panel panel-default">
        <div class="panel-heading">基本设置</div>
        <div class="panel-body">

            <form role="form" class="form-horizontal" onsubmit="return false;">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="">账号：</label>
                    <div class="col-sm-10">
                        <p style="padding-top:7px;"><strong>{{user.account}}</strong></p>
                        <p>当前用户账号为空，可以设置您的账号</p>
                    </div>
                </div>
                <div class="form-group-separator"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="">昵称：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="昵称" v-model="user.nick">
                    </div>
                </div>
                <!-- <div class="form-group-separator"></div> -->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="">真实姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="真实姓名" v-model="user.realName">
                    </div>
                </div>
                <!-- <div class="form-group-separator"></div> -->
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别：</label>
                    <div class="col-sm-10">
                        <p>
                        <label class="radio-inline"><input type="radio" name="sex" value="1" v-model="user.gender">帅哥</label>
                        <label class="radio-inline"><input type="radio" name="sex" value="2" v-model="user.gender">美女</label>
                        </p>
                    </div>
                </div>
                <!-- <div class="form-group-separator"></div> -->
                <div class="form-group">
                    <label class="col-sm-2 control-label">生日：</label>
                    <div class="col-sm-10">
                        <p>
                        <label class="radio-inline" style="padding-left: 0;">
                        <select style="min-width: 70px;" v-model="user.birthYear">
                            <option>----</option>
                            <option v-for="year in years" :value="year">{{year}}</option>
                        </select>年</label>
                        <label class="radio-inline" style="padding-left: 0;">
                        <select style="min-width: 70px;" v-model="user.birthMonth">
                            <option>--</option>
                            <option v-for="month in months" :value="month">{{month}}</option>
                        </select>月</label>
                        <label class="radio-inline" style="padding-left: 0;">
                        <select style="min-width: 70px;" v-model="user.birthDay">
                            <option>--</option>
                            <option v-for="day in days" :value="day">{{day}}</option>
                        </select>日</label>
                        </p>
                    </div>
                </div>
                <!-- <div class="form-group-separator"></div> -->
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="">自我介绍：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" style="height: 70px;" v-model="user.summary"></textarea>
                    </div>
                </div>
                <div class="form-group-separator"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">&nbsp;</label>
                    <div class="col-sm-10">
                        <button type="button" class="btn btn-blue" v-on:click="submit">保存设置</button>
                    </div>
                </div>
            </form>
            
        </div>
    </div>
</script>

<script type="text/x-template" id="songm-user-avatar-template">
    <div class="panel panel-default" ref="avatar">
        <div class="panel-heading">头像修改</div>
        <div class="panel-body">

            <div class="row">
	               <div class="col-md-7">
	                   <strong>原始图片</strong><br>
	                   <div class="img-container">
	                   <img src="" class="img-responsive cropper-hidden">
	                   </div>
	                   <button type="button" class="btn btn-secondary" id="dropzone-imgupload" style="margin-bottom:30px;">上传图片</button>
	               </div>
	               <div class="col-md-5">
	                   <strong>截取图片</strong><br>
                       <div class="img-shade">
                        <div id="img-preview" class="img-preview"></div>
                       </div>
                       <button type="button" class="btn btn-secondary" v-on:click="cutFigure">保存设置</button>
	               </div>
	        </div>
            
        </div>
    </div>
</script>