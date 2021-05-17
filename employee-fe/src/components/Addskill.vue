<template>
<div>
    <h3>Add Skill form</h3>
    <div class='container'>
        <form v-on:submit="addSkillDB">
            <label for="userid">Employee ID:</label>
            <input type="number" id='userid' name='userid' placeholder="Enter ID" v-model='id'>

            <label for="fname">Field name:</label>
            <input type="text" id='fname' name='fname' placeholder="ex: Java" v-model='newSkill.field.field_name'>

            <label for="ftype">Field type:</label>
            <input type="text" id='ftype' name='ftype' placeholder="ex: Software Development" v-model='newSkill.field.field_type'>

            <label for="experience">Exerience:</label>
            <input type="number" id='experience' name='experience' placeholder="Months of Exp: ex '24'" v-model='newSkill.experience'>

            <label for="summary">Summary:</label>
            <textarea id="summary" name="summary" placeholder="Summary of skill" style="height:200px"
            v-model='newSkill.summary'></textarea>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
</template>

<script>

import employeeAPI from '../services/employeeAPI'

export default {
    name: 'add-skill',
    data(){
        return {
            id: 0,
            newSkill: {
                field: {}
            }
        }
    },

    methods: {
        addSkillDB(){
            employeeAPI.addSkillByID(this.id,this.newSkill).then(
                () => {
                    window.alert("added skill");
                    this.newSkill = {};
                    this.$router.push({name: 'Home'});
                }
            ).catch((error) => {
                if(error.response){
                    window.alert('exception caught');
                }
            })
        }
    }

}
</script>

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=number]{
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.container form label {
    float: left;
}
</style>