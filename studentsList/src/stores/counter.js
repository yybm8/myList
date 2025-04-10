import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

 const useCounterStore = defineStore('counter', () => {
  var tableData = ref([])

  const addData=(value)=>{
    this.tableData.push(value)
  
  }
  const nullData=()=>{
    this.tableData[{}]
  
  }

  
  

  return { tableData,addData,nullData}
},{
  persist: true,//持久化设置
},)

export default useCounterStore
