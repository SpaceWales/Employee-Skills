<template>
  <pre class='printlist'>
      <h4>Total Employees: {{totalEmp}} </h4>
    <div class='emp' v-for='employee in employeeList' :key='employee.id'><pre> {{employee || pretty}} </pre>
    </div> 
  </pre>
</template>

<script>

import employeeAPI from '../services/employeeAPI.js'

export default {
    name: 'GetAll',
    data(){
    return {
        employeeList: []
    }
    },
    created() {
        employeeAPI.getAllEmployees().then(
            (response) => {
                this.employeeList = response.data;
            }
        )
    },
    filters: {
        pretty: function(value) {
            return JSON.stringify(JSON.parse(value),null,2);
        }
    },
    computed : {
        totalEmp: function() {
            let count = 0;
            this.employeeList.forEach(() => {
                count++;
            })
            return count;
        }
    }

}
</script>

<style>
.printlist {
    display: flex;
    flex-direction: column;
    text-align: left;
}
.emp {
    border: 1px solid black;
    width: 40%;
}
</style>