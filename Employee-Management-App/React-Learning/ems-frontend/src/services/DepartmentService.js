import axios from "axios";

const REST_API_BASE_URL_DEPT = 'http://localhost:8080/api/departments';

export const listDepartments = () => axios.get(REST_API_BASE_URL_DEPT);
export const createDepartment = (department) => axios.post(REST_API_BASE_URL_DEPT,department);
export const getDepartmentById = (departmentId) => axios.get(REST_API_BASE_URL_DEPT + '/' + departmentId);
export const updateDepartment = (departmentId,department) => axios.put(REST_API_BASE_URL_DEPT + '/'+ departmentId,department);
export const deleteDepartment = (departmentId) => axios.delete(REST_API_BASE_URL_DEPT+ '/' +departmentId);