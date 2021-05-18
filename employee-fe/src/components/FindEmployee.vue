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
        <button
          type="submit"
          class="searchButton"
          v-on:click.prevent="searchEmployee"
        >
          <i class="fa fa-search">Search</i>
        </button>
      </div>
    </div>

    <div class="search-results">
      <pre> {{employee || pretty}} </pre>
    </div>
  </div>
</template>

<script>
import employeeAPI from "../services/employeeAPI";
export default {
  name: "find-employee",
  data() {
    return {
      id: 0,
      employee: {},
    };
  },

  filters: {
    pretty: function (value) {
      return JSON.stringify(JSON.parse(value), null, 2);
    },
  },

  methods: {
    searchEmployee() {
      employeeAPI
        .findEmployee(this.id)
        .then((response) => {
          this.employee = response.data;
        })
        .catch((error) => {
          if (error.response) {
            window.alert("bad request");
          }
        });
    },
  },
};
</script>

<style>
</style>