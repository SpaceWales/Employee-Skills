import axios from 'axios';

 const http = axios.create({
   baseURL: "http://localhost:8080"
 });

 export default {
     
    getAllEmployees()
    {
        return http.get('/employees');
    },
    createEmployee(employee)
    {
      return http.post('/employees',employee);
    },

    allSkillsByID(id)
    {
      return http.get(`/employees/${id}/skills`);
    }


    //in rules if needed:  "vue/no-unused-components": "off"

 }