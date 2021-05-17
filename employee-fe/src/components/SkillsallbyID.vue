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
          v-on:click.prevent="searchSKills"
        >
          <i class="fa fa-search">Search</i>
        </button>
      </div>
    </div>
    <div class="search-results">
      <pre>
      <h4>Total Skills returned for employee: {{totalSkill}} </h4>
      <div v-for='skill in skills' :key='skill.id'> <pre id='skill-box'> {{skill || pretty}} </pre></div>
    </pre>
    </div>
  </div>
</template>

<script>
import employeeAPI from "../services/employeeAPI.js";

export default {
  name: "skills-all-by-id",
  data() {
    return {
      id: 0,
      skills: [],
    };
  },

  filters: {
    pretty: function (value) {
      return JSON.stringify(JSON.parse(value), null, 2);
    },
  },

  methods: {
    searchSKills() {
      employeeAPI.allSkillsByID(this.id).then((response) => {
        this.skills = response.data;
      });
    },
  },

  computed: {
    totalSkill: function () {
      let count = 0;
      this.skills.forEach(() => {
        count++;
      });
      return count;
    },
  },
};
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

body {
  background: #f2f2f2;
  font-family: "Open Sans", sans-serif;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid #00b4cc;
  border-right: none;
  padding: 5px;
  height: 35px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9dbfaf;
}

.searchTerm:focus {
  color: #00b4cc;
}

.searchButton {
  width: 100px;
  height: 36px;
  border: 1px solid #00b4cc;
  background: #00b4cc;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}

.wrap {
  width: 30%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.search-results {
  display: flex;
  flex-direction: column;
  text-align: left;
  margin-top: 500px;
}

#skill-box {
  border: 1px solid black;
  width: 25%;
}
</style>