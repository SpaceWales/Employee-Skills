<template>
  <div>
    <h3>Employee Create Form</h3>

    <div class="container">
      <form v-on:submit="createEmployeeDB">
        <label for="fname">First Name</label>
        <input
          type="text"
          id="fname"
          name="firstname"
          placeholder="John"
          v-model="newEmployee.firstname"
        />

        <label for="lname">Last Name</label>
        <input
          type="text"
          id="lname"
          name="lastname"
          placeholder="Doe"
          v-model="newEmployee.lastname"
        />

        <label for="contactemail">Contact Email</label>
        <input
          type="email"
          id="contact-email"
          name="contact-email"
          placeholder="youremail@domain.com"
          v-model="newEmployee.contactemail"
        />

        <label for="companyemail">Company Email</label>
        <input
          type="email"
          id="company-email"
          name="company-email"
          placeholder="business@domain.com"
          v-model="newEmployee.companyemail"
        />

        <label for="birthdate">Birthday</label>
        <input
          type="date"
          id="birthdate"
          name="birthdate"
          placeholder="1990-01-01"
          v-model="newEmployee.birthdate"
        />

        <label for="hireddate">Hire Date</label>
        <input
          type="date"
          id="hireddate"
          name="hireddate"
          placeholder="2021-01-01"
          v-model="newEmployee.hireddate"
        />
        <h3>Address:</h3>

        <label for="address">Street Address</label>
        <input
          type="text"
          id="street"
          name="street"
          placeholder="123 Nowhere Lane"
          class="address-input"
          v-model="newEmployee.address.street"
        />

        <label for="suite">Suite</label>
        <input
          type="text"
          id="suite"
          name="suite"
          placeholder="Suite 1A"
          class="address-input"
          v-model="newEmployee.address.suite"
        />

        <label for="city">City</label>
        <input
          type="text"
          id="city"
          name="city"
          placeholder="Detroit"
          class="address-input"
          v-model="newEmployee.address.city"
        />

        <label for="postal">Postal</label>
        <input
          type="text"
          id="postal"
          name="postal"
          placeholder="90210"
          maxlength="5"
          v-model="newEmployee.address.postal"
        />

        <label for="country">Country</label>
        <input
          type="text"
          id="country"
          name="country"
          placeholder="US"
          maxlength="2"
          v-model="newEmployee.address.country"
        />

        <input type="submit" value="Submit" />
        <!-- <select id="country" name="country">
      <option value="australia">Australia</option>
      <option value="canada">Canada</option>
      <option value="usa">USA</option>
    </select> -->

        <!-- <label for="subject">Subject</label>
    <textarea id="subject" name="subject" placeholder="Write something.." style="height:200px"></textarea> -->
      </form>
    </div>
  </div>
</template>

<script>
import employeeAPI from "../services/employeeAPI.js";

export default {
  name: "create-employee",
  data() {
    return {
      newEmployee: {
        address: {},
      },
    };
  },
  methods: {
    createEmployeeDB() {
      employeeAPI
        .createEmployee(this.newEmployee)
        .then(() => {
          window.alert("Created Employee");
          this.newEmployee = {};
          this.$router.push({ name: "Home" });
        })
        .catch((error) => {
          if (error.response) {
            window.alert("exception caught");
          }
        });
    },
  },
};
</script>

<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}
* {
  box-sizing: border-box;
}

input[type="text"],
select,
textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type="date"] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type="email"] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type="submit"] {
  background-color: #04aa6d;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
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