import {useEffect, useState} from 'react'
import logo from './logo.svg'
import './App.css'
import {Icon} from "@mui/material";
import {EmployeeForm} from "./Pages/EmployeeForm/EmployeeForm";
import {useEmployeeForm} from "./Pages/EmployeeForm/useEmployeeForm";
import { Routes, Route, Link } from "react-router-dom";
import {EmployeeList} from "./Pages/EmployeeList/EmployeeList";
import {useEmployeeList} from "./Pages/EmployeeList/useEmployeeList";


function App() {
    return (
    <div>
      <Routes>
          <Route path="/" element={
              <h1>Welcome <br/>
                  <a href="/employee">Go to form</a>
              </h1>
          }/>
              <Route path="/employee" element={ <EmployeeForm {...useEmployeeForm()}/>}/>
              <Route path="/employeeList" element={ <EmployeeList {...useEmployeeList()}/>}/>
      </Routes>
    </div>
  )
}

export default App;
