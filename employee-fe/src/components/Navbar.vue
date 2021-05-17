<template>
  <div class="navbar">
    <router-link to="/">Home</router-link>
    <div class="dropdown">
      <button class="dropbtn">
        Employee
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <router-link to="/getall">Get All Employees</router-link>
        <router-link to="/create-employee">Create Employee</router-link>
        <a href="#">Find</a>
        <router-link to="/update-employee">Update Employee</router-link>
        <router-link to="/delete-employee">Delete Employee</router-link>
      </div>
    </div>
    <div class="dropdown">
      <button class="dropbtn">
        Skills
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="dropdown-content">
        <router-link to="/skills-by-id">Get Skills by Id</router-link>
        <router-link to="/add-skill">Add Skill</router-link>
        <a href="#">Find</a>
        <a href="#">Update</a>
        <a href="#">Delete</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
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