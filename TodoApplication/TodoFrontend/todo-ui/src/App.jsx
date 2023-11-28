import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import Footer from './components/Footer'
import Header from './components/Header'
import ListTodoComponent from './components/ListTodoComponent'
import TodoComponent from './components/TodoComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './utilities/AuthService'

function App() {

  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }

    return <Navigate to="/" />
  }

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          {/* // htpp://localhost:8080/ */}
          <Route path='/' element={ <LoginComponent /> } />
          {/* // htpp://localhost:8080/todos */}
          <Route path='/todos' element={
            <AuthenticatedRoute>
              <ListTodoComponent />
            </AuthenticatedRoute>
          } />
          {/* // htpp://localhost:8080/add-todo */}
          <Route path='/add-todo' element={
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute>
          } />

          {/* // htpp://localhost:8080/update-todo/id */}
          {/* //to specify router that there is path variable within url we need to use :ID */}
          <Route path='/update-todo/:id' element={
            <AuthenticatedRoute>
              <TodoComponent />
            </AuthenticatedRoute>
          } />

          {/* // htpp://localhost:8080/register */}
          <Route path='/register' element={<RegisterComponent />} />

          {/* // htpp://localhost:8080/login */}
          <Route path='/login' element={<LoginComponent />} />

        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  )
}

export default App
