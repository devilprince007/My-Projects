import { useEffect, useState } from "react"
import ListDepBodyComponents from "./ListDepBodyComponents";
import { listDepartments } from "../services/DepartmentService";
import { Link } from "react-router-dom";

const ListDepartmentComponent = () => {

    const [departments,setDepartments] = useState([]);

    useEffect(() => {
        getAllDepartment()
    }, [])

    function getAllDepartment() {
        listDepartments().then(response => {
            setDepartments(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

  return (
    <div className="container">
        <h2 className="text-center">List Of Departments</h2>
        <Link to='/add-department' className="btn btn-primary mb-2">Add Department</Link>
        <table className="table table-dark table-hover table-striped table-bordered">
            <thead>
                <tr>
                    <th>Department ID</th>
                    <th>Department Name</th>
                    <th>Department Discription</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <ListDepBodyComponents onDeleteId={getAllDepartment} departmentList={departments} />
        </table>
    </div>
  )
}

export default ListDepartmentComponent