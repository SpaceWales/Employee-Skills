<template>
  <div>
    <div class="navbar">
      <a href="#home">Home</a>
      <div class="dropdown">
        <button class="dropbtn">
          Employee
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
          <a v-on:click.prevent="showAllEmployee = !showAllEmployee" href="#"
            >Get All</a
          >
          <a
            v-on:click.prevent="showCreateEmployee = !showCreateEmployee"
            href="#"
            >Create</a
          >
          <a href="#">Find</a>
          <a href="#">Update</a>
          <a href="#">Delete</a>
        </div>
      </div>
      <div class="dropdown">
        <button class="dropbtn">
          Skills
          <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
          <a v-on:click.prevent="showSearchSkill = !showSearchSkill" href="#"
            >Get by Employee</a
          >
          <a v-on:click.prevent="showAddSkill = !showAddSkill" href="#">Add</a>
          <a href="#">Find</a>
          <a href="#">Update</a>
          <a href="#">Delete</a>
        </div>
      </div>
    </div>

    <h3>Choose option from dropdown</h3>

    <div v-show="showAllEmployee" id="show-employee"><get-all /></div>
    <div v-show="showCreateEmployee" id="create-employee">
      <create-employee />
    </div>
    <div v-show="showSearchSkill" id="show-skill-id"><skillid /></div>
    <div v-show="showAddSkill" id="show-add-skill"><addskill /></div>
  </div>
</template>

<script>
import GetAll from "../components/Getall.vue";
import CreateEmployee from "../components/CreateEmp.vue";
import skillid from "../components/SkillsallbyID.vue";
import addskill from "../components/Addskill.vue";

export default {
  name: "Home",
  data() {
    return {
      showAllEmployee: false,
      showCreateEmployee: false,
      showSearchSkill: false,
      showAddSkill: false,
    };
  },

  components: {
    GetAll,
    CreateEmployee,
    skillid,
    addskill,
  },
};
</script>

<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

.navbar {
  overflow: hidden;
  background-color: #333;
}

.navbar a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover,
.dropdown:hover .dropbtn {
  background-color: red;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>