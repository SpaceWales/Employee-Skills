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
    },

    addSkillByID(id,skill)
    {
      return http.post(`/employees/${id}/skills`,skill);
    },

    getEmployee(id)
    {
      return http.get(`/employees/${id}`);
    },

    updateEmployee(id,employee)
    {
      return http.put(`/employees/${id}`,employee);
    },

    deleteEmployee(id)
    {
      return http.delete(`/employees/${id}`);
    },

    findEmployee(id)
    {
      return http.get(`/employees/${id}`);
    }

    

    //in rules if needed:  "vue/no-unused-components": "off"

 }