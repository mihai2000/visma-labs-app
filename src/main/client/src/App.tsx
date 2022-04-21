import {useEffect, useState} from 'react'
import logo from './logo.svg'
import './App.css'
import {Icon} from "@mui/material";
import {EmployeeForm} from "./Pages/EmployeeForm/EmployeeForm";
import {useEmployeeForm} from "./Pages/EmployeeForm/useEmployeeForm";

function App() {
  // const [count, setCount] = useState(0)
  // let number = 0
  //
  // useEffect(()=> {
  //   window.fetch('/api/employee/1')
  //       .then(r => r.json())
  //       .then(resp=> console.log(resp))
  // },[])//array de dependinte, a aparut pagina pe ecran, lanseaza useEffect

  return (
    <div>
    <EmployeeForm {...useEmployeeForm()}/>
    </div>
  )
}

export default App
