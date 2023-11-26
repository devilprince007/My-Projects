import { useNavigate } from "react-router-dom"
import { deleteDepartment } from "../services/DepartmentService"

const ListDepBodyComponents = ({ departmentList, onDeleteId }) => {

    const navigator = useNavigate()

    function updateDepartment(id){
        navigator(`/edit-department/${id}`)
    }

    function deleteDept(id){
        console.log(id)
        deleteDepartment(id).then((response) =>{
            console.log(response.data)
            onDeleteId()
        }).catch(error => {
            console.error(error);
        })
    }

  return (
    <>
        <tbody>
            {
                departmentList.map(department =>
                    <tr key={department.id}>
                        <td>{department.id}</td>
                        <td>{department.departmentName}</td>
                        <td>{department.departmentDiscription}</td>
                        <td>
                            <button className="btn btn-info" onClick={() => updateDepartment(department.id)}>Update</button>
                            <button className="btn btn-danger" onClick={() => deleteDept(department.id)}
                                style={{marginLeft: '20px'}}
                            >Delete</button>
                        </td>
                    </tr>)
            }
        </tbody>
    </>
  )
}

export default ListDepBodyComponents