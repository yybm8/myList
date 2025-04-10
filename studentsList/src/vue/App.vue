 
<script setup>
 
</script>
 
<template>
      <div class="common-layout">
    <el-container style="height: 100vh;">
        <el-header>
            <el-menu :router="true">
                <el-menu-item index="/List">
                    <span>List</span>
                </el-menu-item>
            </el-menu>

        </el-header>
        <el-footer>
            <el-main >

                <router-view></router-view>



            </el-main>
        </el-footer>
    </el-container>
</div>
 
</template>
 
<style scoped>
.common-layout{
  width: 100%;
}
.el-header{
    border :1px solid rgb(202, 202, 202);
}
.el-footer{
    height: 900px;
}


 
</style>