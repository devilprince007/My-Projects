import { useEffect, useState } from "react"
import { addTodo, getTodo, updateTodo } from "../utilities/TodoService"
import { useNavigate, useParams } from "react-router-dom"


const TodoComponent = () => {

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [completed, setCompleted] = useState(false)

    // catching id from parameter as the page loads
    const navigator = useNavigate();
    const { id } = useParams()

    function saveOrUpdateTodo(e) {

        e.preventDefault()
        const todo = { title, description, completed }
        console.log(todo)

        if (id) {
            updateTodo(id, todo).then((response) => {
                console.log(response.data)
                navigator('/todos')
            }).catch(error => {
                console.log(error)
            })
        } else {
            addTodo(todo).then((response) => {
                console.log(response.data)
                navigator('/todos')
            }).catch(error => {
                console.log(error)
            })
        }
    }

    //will check on page load id is present or not
    function pageTitle() {
        if (id) {
            // console.log(id)
            return <h1 className="text-center">Update Todo</h1>
        } else {
            return <h1 className="text-center">Add Todo</h1>
        }
    }

    useEffect(() => {
        if (id) {
            console.log(id)
            getTodo(id).then(response => {
                setTitle(response.data.title);
                setDescription(response.data.description);
                setCompleted(response.data.completed);
            }).catch(error => {
                console.log(error);
            })
        }
    }, [])

    return (
        <div className="container">
            <br /> <br />
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        // calling page title function to determine which title should rander
                        //based on id is present or not title will rander
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">Todo Title: </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Enter Todo Title"
                                    name='title'
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                />
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Todo Description: </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Enter Todo Title"
                                    name='description'
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                            </div>

                            <div className="form-group mb-2">
                                <label className="form-label">Todo Completed: </label>
                                <select
                                    className="form-control"
                                    value={completed}
                                    onChange={(e) => setCompleted(e.target.value)}
                                >
                                    <option value="false">No</option>
                                    <option value="true">Yes</option>
                                </select>
                            </div>
                            <button type="submit" className="btn btn-success" onClick={(e) => saveOrUpdateTodo(e)}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default TodoComponent