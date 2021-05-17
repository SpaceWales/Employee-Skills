<template>
  <div>
    <div class="wrap">
      <h3>Enter Employee ID</h3>
      <div class="search">
        <input
          type="number"
          class="searchTerm"
          placeholder="Enter ID to search"
          v-model="id"
        />
        <button type="submit" class="searchButton" v-on:click.prevent="hydrateEmployee"></button>
      </div>
    </div>

    <div class="search-results">
      <div class="container">
        <form v-on:submit="createEmployeeDB">
          <label for="fname">First Name</label>
          <input
            type="text"
            id="fname"
            name="firstname"
            placeholder="John"
            v-model="employee.firstname"
          />

          <label for="lname">Last Name</label>
          <input
            type="text"
            id="lname"
            name="lastname"
            placeholder="Doe"
            v-model="employee.lastname"
          />

          <label for="contactemail">Contact Email</label>
          <input
            type="email"
            id="contact-email"
            name="contact-email"
            placeholder="youremail@domain.com"
            v-model="employee.contactemail"
          />

          <label for="companyemail">Company Email</label>
          <input
            type="email"
            id="company-email"
            name="company-email"
            placeholder="business@domain.com"
            v-model="employee.companyemail"
          />

          <label for="birthdate">Birthday</label>
          <input
            type="date"
            id="birthdate"
            name="birthdate"
            placeholder="1990-01-01"
            v-model="employee.birthdate"
          />

          <label for="hireddate">Hire Date</label>
          <input
            type="date"
            id="hireddate"
            name="hireddate"
            placeholder="2021-01-01"
            v-model="employee.hireddate"
          />
          <h3>Address:</h3>

          <label for="address">Street Address</label>
          <input
            type="text"
            id="street"
            name="street"
            placeholder="123 Nowhere Lane"
            class="address-input"
            v-model="employee.address.street"
          />

          <label for="suite">Suite</label>
          <input
            type="text"
            id="suite"
            name="suite"
            placeholder="Suite 1A"
            class="address-input"
            v-model="employee.address.suite"
          />

          <label for="city">City</label>
          <input
            type="text"
            id="city"
            name="city"
            placeholder="Detroit"
            class="address-input"
            v-model="employee.address.city"
          />

          <label for="postal">Postal</label>
          <input
            type="text"
            id="postal"
            name="postal"
            placeholder="90210"
            maxlength="5"
            v-model="employee.address.postal"
          />

          <label for="country">Country</label>
          <input
            type="text"
            id="country"
            name="country"
            placeholder="US"
            maxlength="2"
            v-model="employee.address.country"
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
  </div>
</template>

<script>
import employeeAPI from '../services/employeeAPI';
export default {
  data() {
    return {
      id: 0,
      employee: {
        address: {}
      },
    };
  },

  filters: {
    pretty: function (value) {
      return JSON.stringify(JSON.parse(value), null, 2);
    },
  },

  methods: {
    
    hydrateEmployee(id){
      employeeAPI.getEmployee(this.id).then((response) => {
        this.employee = response.data;
      });
    },


  }
};
</script>

<style>
</style>