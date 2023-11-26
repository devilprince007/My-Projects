import { useEffect, useState } from "react"
import { createDepartment, getDepartmentById, updateDepartment } from "../services/DepartmentService";
import { useNavigate, useParams } from "react-router-dom";

const DepartmentComponent = () => {

    const [departmentName, setDepartmentName] = useState('');
    const [departmentDiscription, setDepartmentDiscription] = useState('');

    const {id} = useParams();

    const navigator = useNavigate();

    function saveOrUpdateDepartment(e) {
        e.preventDefault();

        const department = {departmentName, departmentDiscription};
        console.log(department);
        if(id){
            updateDepartment(id,department).then((response) => {
                console.log(response.data);
                navigator('/departments')
            }).catch(error =>{
                console.error(error);
            })
        } else {
            createDepartment(department).then((response) =>{
                console.log(response.data);
                navigator('/departments')
            }).catch(error =>{
                console.log(error);
            })
        }
    }

    function pageTitle(){
        if(id){
            return <h2 className="text-center">Update Department</h2>
        } else {
            return <h2 className="text-center">Add Department</h2>
        }
    }

    // to get department by id
    useEffect(() => {
        getDepartmentById(id).then((response) => {
            setDepartmentName(response.data.departmentName);
            setDepartmentDiscription(response.data.departmentDiscription);
        }).catch(error => {
            console.error(error);
        })
    },[id])

    return (
        <div className="container">
            <br/> <br/>
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">Department Name : </label>
                                <input
                                    type="text"
                                    placeholder="Enter Department's Name"
                                    name='departmentName'
                                    value={departmentName}
                                    onChange={(e) => setDepartmentName(e.target.value)}
                                    className="form-control"
                                ></input>
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Department Discription : </label>
                                <input
                                    type="text"
                                    placeholder="Enter Department's Discription"
                                    name='departmentDiscription'
                                    value={departmentDiscription}
                                    onChange={(e) => setDepartmentDiscription(e.target.value)}
                                    className="form-control"
                                ></input>
                            </div>
                            <button className="btn btn-success" onClick={(e) => saveOrUpdateDepartment(e)}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DepartmentComponent