import { useEffect, useState } from "react"
import { getAllTodos, deleteTodo, completeTodo, inCompleteTodo } from "../utilities/TodoService";
import { useNavigate } from "react-router-dom";
import { isAdminUser } from "../utilities/AuthService";

const ListTodoComponent = () => {

  const [todos, setTodos] = useState([]);
  const navigator = useNavigate();

  const isAdmin = isAdminUser();

  //use effect will be used as soon the component load it will call this lisTodo function defined below
  useEffect(() => {
    listTodos();
  }, [])

  // will fecth data from db with the help of getAllTodo function defined in utils
  function listTodos() {
    getAllTodos().then(response => {
      setTodos(response.data);
    }).catch(error => {
      console.log(error);
    })
  }

  function addNewTodo() {
    navigator('/add-todo');
  }

  function updateTodo(id) { // taking paramter as a argument from the onclick event
    //calling useNavigate funtion to route it to this path using route with the id variable
    navigator(`/update-todo/${id}`)
  }


  function removeTodo(id) {
    deleteTodo(id).then(response => {
      console.log(response)
      //actuuly we are on the same component so after deleting we have to refetch all the todos
      //so we will call listTodos() again to referesh list
      //Here we cannot call navigate funtion because its already on same component which will show old data when called
      listTodos();
    }).catch(error => {
      console.log(error)
    })
  }

  function markCompleteTodo(id){
    completeTodo(id).then(response => {
      listTodos();
    }).catch(error => {
      console.log(error)
    })
  }

  function markInCompleteTodo(id){
    inCompleteTodo(id).then(response => {
      listTodos();
    }).catch(error => {
      console.log(error)
    })
  }

  return (
    <div className="container">
      <h2 className="text-center">List Of Todos</h2>
      {
        isAdmin &&
        <button className="btn btn-primary mb-2" onClick={addNewTodo}>Add Todo</button>
      }
      <div>
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Todo ID</th>
              <th>Todo Title</th>
              <th>Todo Description</th>
              <th>Todo Completed</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            { //map function to fetch data from array and show it like this
              todos.map(todo =>
                <tr key={todo.id}>
                  <td>{todo.id}</td>
                  <td>{todo.title}</td>
                  <td>{todo.description}</td>
                  <td>{todo.completed ? 'Yes' : 'No'}</td>
                  <td>
                    {
                      isAdmin &&
                      <button className="btn btn-info" onClick={() => updateTodo(todo.id)}>Update</button>
                    }
                    {
                      isAdmin &&
                      <button className="btn btn-danger" onClick={() => removeTodo(todo.id)} style={{ margin: "10px" }}>Delete</button>
                    }
                    <button className="btn btn-success" onClick={() => markCompleteTodo(todo.id)} style={{ margin: "10px" }}>Completed</button>
                    <button className="btn btn-warning" onClick={() => markInCompleteTodo(todo.id)} style={{ margin: "10px" }}>In-Complete</button>
                  </td>
                </tr>
              )
            }
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default ListTodoComponent