import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Getall from '../components/Getall.vue'
import CreateEmp from '../components/CreateEmp.vue'
import SkillAllByID from '../components/SkillsallbyID.vue'
import AddSkill from '../components/Addskill.vue'
import UpdateEmployee from '../components/UpdateEmployee.vue'
import DeleteEmployee from '../components/DeleteEmployee.vue'
import FindEmployee from '../components/FindEmployee.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },

  {
    path: '/getall',
    name: 'getall',
    component: Getall
  },

  {
    path: '/create-employee',
    name: 'create-employee',
    component: CreateEmp
  },

  {
    path:'/skills-by-id',
    name:'skills-by-id',
    component: SkillAllByID
  },

  {
    path:'/add-skill',
    name:'add-skill',
    component: AddSkill
  },

  {
    path:'/update-employee',
    name:'update-employee',
    component: UpdateEmployee
  },

  {
    path:'/delete-employee',
    name:'delete-employee',
    component: DeleteEmployee
  },

  {
    path:'/find-employee',
    name:'find-employee',
    component: FindEmployee
  }


  
  

]

const router = new VueRouter({
  routes
})

export default router
