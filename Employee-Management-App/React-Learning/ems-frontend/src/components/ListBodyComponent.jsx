import { useNavigate } from "react-router-dom"
import { deleteEmployee } from "../services/EmployeeService";

const ListBodyComponent = ({ employeeList, onDeleteId }) => {

    const navigator = useNavigate();

    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }

    function removeEmployee(id){
        console.log(id);
        deleteEmployee(id).then((response) =>{
            onDeleteId()
        }).catch(error => {
            console.error(error);
        })
    }

    return (
        <>
            <tbody>
                {
                    employeeList.map(employee =>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>
                                <button className="btn btn-info" onClick={() => updateEmployee(employee.id)}>Update</button>
                                <button className="btn btn-danger" onClick={() => removeEmployee(employee.id)}
                                    style={{marginLeft: '20px'}}
                                >Delete</button>
                            </td>
                        </tr>)
                }
            </tbody>
        </>
    )
}

export default ListBodyComponent