<template>
  <div>
    <div class="wrap">
      <h3>Enter Employee ID</h3>
      <div class="search">
        <input
          type="number"
          class="searchTerm"
          placeholder="Enter ID to delete"
          v-model="id"
        />
        <button v-on:click.prevent='deleteEmployee'>Submit</button>
      </div>
    </div>
  </div>
</template>

<script>

import employeeAPI from "../services/employeeAPI.js";

export default {
    id: 0,
    methods: {
        deleteEmployee(){
            employeeAPI.deleteEmployee(this.id).then(()=>{
                window.alert('deleted employee');
            })
            .catch((error) => {
                if(error.response){
                    window.alert('something went wrong');
                }
            })
            this.$router.push({name: 'Home'});

        }
    }
};



</script>

<style>
</style>