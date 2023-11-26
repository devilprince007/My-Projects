import React, { useEffect, useState } from 'react'
import { listEmployees } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';
import ListBodyComponent from './ListBodyComponent';

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([]);
    const navigator = useNavigate();

    useEffect(() =>{
           getAllEmployees();
    }, [] )

    function getAllEmployees(){
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error("Something went wrong!");
        })
    }

    function addNewEmployee() {
        navigator('/add-employee')
    }

  return (
    <div className='container'>
        <h2 className='text-center'>List of Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-dark table-hover table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email Id</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <ListBodyComponent onDeleteId={getAllEmployees} employeeList={employees} />
        </table>
    </div>
  )
}

export default ListEmployeeComponent