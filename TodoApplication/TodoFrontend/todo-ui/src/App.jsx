import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Footer from './components/Footer'
import Header from './components/Header'
import ListTodoComponent from './components/ListTodoComponent'
import TodoComponent from './components/TodoComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          {/* // htpp://localhost:8080/ */}
          <Route path='/' element={ <ListTodoComponent /> }/>
          {/* // htpp://localhost:8080/todos */}
          <Route path='/todos' element={ <ListTodoComponent /> }/>
          {/* // htpp://localhost:8080/add-todo */}
          <Route path='/add-todo' element={ <TodoComponent /> }/>

          {/* // htpp://localhost:8080/update-todo/id */} 
          {/* //to specify router that there is path variable within url we need to use :ID */}
          <Route path='/update-todo/:id' element={ <TodoComponent /> }/>

        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  )
}

export default App
