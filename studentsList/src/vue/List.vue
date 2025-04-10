<template>
  <div>
    <el-table :data="data.tableData" stripe style="width: 100%" current-row-key="data.tableData.id">
      <el-table-column prop="username" label="姓名" width="180" />
      <el-table-column prop="password" label="密码" width="180" />
      <el-table-column fixed="right" label="选项  " min-width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="data.selectDao=true,data.all=scope.row,console.log(data.all)">
            查看
          </el-button>
          <el-button link type="primary" size="small" @click="deleteData( scope.row)">
            删除
          </el-button>
          <el-button link type="primary" size="small"
           @click="data.updateDao = true,
             data.usernameU=scope.row.username,
            data.passwordU=scope.row.password,
            data.ageU=scope.row.age,
            data.updatepage=scope.row
        ">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="select">
      <span>
        <select v-model="data.pageSize1" @change="changePage()">
          <option disabled value="">Please select one</option>
          <option>5</option>
          <option>10</option>
          <option>20</option>
        </select>
      </span>
      <span>当前页面/总页面:<span>{{data.page1}}/{{ data.countPage }}</span></span>
      <!-- TODO 点击跳转页面(上一页)1 2 3 ... n（下一页） -->
       <span><button  v-bind:disabled="data.ok1" @click="pagePrev()">上一页</button></span>
      <span><button style="background-color: aqua;">{{ data.page1 }}</button></span>
      <span v-if="!(data.page1+1>=data.countPage)"><button @click="selectDao(data.page1+1),data.page1++,data.ok1=false,data.ok2=false">{{ data.page1+1 }}</button></span>
      <span v-if="!(data.page1+2>=data.countPage)"><button @click="selectDao(data.page1+2),data.page1=data.page1+2,data.ok1=false,data.ok2=false">{{ data.page1+2 }}</button></span>
      <span v-if="!(data.page1+3>=data.countPage)">...</span>
      <span v-if="!(data.page1==data.countPage)"><button @click="selectDao(data.countPage),data.page1=data.countPage">{{ data.countPage }}</button></span>
      <span><button v-bind:disabled="data.ok2" @click="pageNext()">下一页</button></span>



      <input type="text" v-model="data.usernames">
      <input type="text" v-model="data.passwords">
      <el-button type="button" @click="usPa()">查询</el-button>
    </div>
    <div>
      <span><el-button type="button" @click="data.addDao = true">新增</el-button></span>
    </div>


    <!-- 新增页面弹窗 -->
    <el-dialog v-model="data.addDao" width="500" align-center>
      <span>新增页面</span>

      <div>
        <span>姓名</span>
        <el-input v-model="data.username" style="width: 240px" placeholder="姓名" clearable />
      </div>
      <div>
        <span>密码</span>
        <el-input v-model="data.password" style="width: 240px" placeholder="密码" clearable />
      </div>
      <div>
        <span>年龄</span>
        <el-input type="Number" v-model=data.age style="width: 240px" placeholder="年龄" clearable />
      </div>

      <div>
        <el-radio-group v-model="data.sex">
          <el-radio value="男" size="large">男</el-radio>
          <el-radio value="女" size="large">女</el-radio>
        </el-radio-group>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="data.addDao = false,insertData()">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 修改页面弹窗 -->
    <el-dialog v-model="data.updateDao" width="500" align-center>
      <span>修改页面</span>


      <div>
        <span>姓名</span>
        <el-input v-model="data.usernameU" style="width: 240px" placeholder="姓名" clearable />
      </div>
      <div>
        <span>密码</span>
        <el-input v-model="data.passwordU" style="width: 240px" placeholder="密码" clearable />
      </div>
      <div>
        <span>年龄</span>
        <el-input type="Number" v-model=data.ageU style="width: 240px" placeholder="年龄" clearable />
      </div>

      <div>
        <el-radio-group v-model="data.sexU">
          <el-radio value="男" size="large">男</el-radio>
          <el-radio value="女" size="large">女</el-radio>
        </el-radio-group>
      </div>

      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="data.updateDao = false,updateData(data.updatepage)">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>

   <!--  删除页面弹窗 
    <el-dialog v-model="data.deleteDao" width="500" align-center>
      <span>删除成功</span>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="data.deleteDao = false">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog> -->
    <!-- 数据判断 -->
    <el-dialog v-model="data.missDao" width="500" align-center>
      <span>请输入有效信息</span>
      <template #footer>
      </template>
    </el-dialog>

    <el-dialog v-model="data.sscDao" width="500" align-center>
      <span>成功</span>
      <template #footer>
      </template>
    </el-dialog>
    <!--TODO 改漂亮点-->
    <el-dialog v-model="data.selectDao" width="500" align-center>
      <span>信息</span>
      <div>姓名:{{ data.all.username }}</div>
      <div>密码:{{ data.all.password }}</div>
      <div>年龄:{{ data.all.age }}</div>
      <div>性别:{{ data.all.sex }}</div>
      <div v-if="data.all.status==1">管理员</div>
      <div v-else>普通用户</div>
      <template #footer>
      </template>
    </el-dialog>


  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, reactive } from 'vue';
import axios from 'axios'
//data总数据
var data = reactive({
  page1: 1,
  pageSize1: null,
  tableData: [],
  count: 0,
  usernames: '',
  passwords: '',
  addDao: false,
  updateDao: false,
  deleteDao: false,
  username: null,
  password: null,
  age:null,
  sex: null,
  missDao:false,
  sscDao:false,
  usernameU: null,
  passwordU: null,
  ageU:null,
  sexU: null,
  updatepage:null,
  selectDao:false,
  all:({id:null,username:null,password:null,age:null,sex:null,status:null}),
  countPage:null,
  ok1:false,
  ok2:false,
})
/* 查询展示页面 */
const usPa = () => {
  if(data.pageSize1!=null){
    data.page1=1
  paSi(data.page1, data.pageSize1, data.usernames, data.passwords)
  }
  else{
    data.missDao=true
  }
}
/* 页面展示数量 */
const changePage = () => {
  data.page1=1
  paSi(data.page1, data.pageSize1, '', '')
}
/* 页面初次加载 */
onMounted(() => {
  paSi(1, 5, '', '')
})
/* 页面axios请求发送 */
function paSi(page1, pageSize1, usernames, passwords) {
  data.tableData = [{}]
  var a = Number(pageSize1)
  var us = String(usernames)
  var pa = String(passwords)
  var students=ref()
  axios({
    url: 'http://127.0.0.1:8080/users/page',
    method: "post",

    data: {
      page: page1,
      pageSize: a,
      username: us,
      password: pa
    }

  }).then((result) => {
    if(result.data.code=='2000'){
     students = ref(result.data.data.data)
      data.count = result.data.data.count
      data.tableData = students.value
      data.countPage=Math.ceil(data.count/a)
      data.ok1=false
      data.ok2=false
    }
    else if(result.data.code=='4001'){
      students = ref(result.data.data.data)//第一个data是后端数据,第二个是后端封装的map数据分为list和键和总数和键，第三个是list数据
      data.count = result.data.data.count
      data.tableData = students.value
      data.countPage=Math.ceil(data.count/a)
      data.ok1=false
      data.ok2=false
    }
    else if(result.data.code=='4000'){
      data.count = 0
      data.tableData = null
      data.ok1=false
      data.ok2=false
    }
    else if(result.data.code=='4002'){
      data.tableData = null
      data.missDao=true
      data.count = 0
      data.ok1=false
      data.ok2=false

    }
  }).catch((error) => {
    console.log("miss:" + error)
  })
}
//添加数据
const insertData=()=>{
  if(data.sex!=null&&data.username!=null&&data.password!=null){
    if(data.age>0&&data.age<200){
      axios({
      url:'http://127.0.0.1:8080/users/insert',
      method:'POST',
      data:{
        username:data.username,
        password:data.password,
        sex:data.sex,
        age:data.age,
        status:1,
      },
    }).then(()=>{
      if(data.pageSize1==null){
    data.pageSize1=5
  }
      data.sscDao=true
      paSi(data.page1, data.pageSize1, data.usernames, data.passwords)

    }).catch((error)=>{
      console.log("miss:" + error)
    })
  }else{
    data.missDao=true
  }

  }
  else{
    data.missDao=true
  }
}
//修改数据
const updateData=(row)=>{
  if(data.sexU!=null&&data.usernameU!=null&&data.passwordU!=null){
    if(data.ageU>0&&data.ageU<200){
    axios({
      url:'http://127.0.0.1:8080/users/update',
      method:'POST',
      data:{
        username:data.usernameU,
        password:data.passwordU,
        age:data.ageU,
        sex:data.sexU,
        status:1,
        id:data.updatepage.id
      }
    }).then(()=>{
      if(data.pageSize1==null){
    data.pageSize1=5
  }
      data.sscDao=true
      paSi(data.page1, data.pageSize1, data.usernames, data.passwords)
    }).catch((error)=>{
      console.log("miss:" + error)
    })
  }
  else{
    data.missDao=true
  }
  }
  else{
    data.missDao=true
  }
}
//删除数据
const deleteData=(row)=>{
  axios({
    url:'http://127.0.0.1:8080/users/delete',
    method:'POST',
    data:{
      id:row.id
    }
  }).then(()=>{
    if(data.pageSize1==null){
    data.pageSize1=5
  }
    data.sscDao=true,
    paSi(data.page1,  data.pageSize1, data.usernames, data.passwords)

  }).catch((error)=>{
    console.log("miss:" + error)
  })

}

const pagePrev=()=>{
  if(data.pageSize1==null){
    data.pageSize1=5
  }
   data.page1--
  if(data.page1<1){
    data.ok1=true
    data.ok2=false
    data.page1++
  }else{
    data.ok2=false
  paSi(data.page1, data.pageSize1, data.usernames, data.passwords)
  }
}
const pageNext=()=>{
  if(data.pageSize1==null){
    data.pageSize1=5
  }
  data.page1++
  if(data.page1>data.countPage){
    data.ok1=false
    data.ok2=true
    data.page1--
  }else{
    data.ok1=false
  paSi(data.page1, data.pageSize1, data.usernames, data.passwords)
  }
}

const selectDao=(row)=>{
  if(data.pageSize1==null){
    data.pageSize1=5
  }
  paSi(row, data.pageSize1, data.usernames, data.passwords)
}

</script>




<style setup>
.select {
  border: 1px solid rgb(217, 217, 217);
  background-color: rgb(238, 238, 238);
}
</style>
