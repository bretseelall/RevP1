import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Navigate, Route, Routes } from 'react-router-dom';
import UserLogin from './Components/UserLogin/UserLogin';
import UserManagement from './Components/UserLogin/UserManagement';
import { UserProvider } from './Components/UserContext/UserContext';
import { AuthProvider } from './Components/UserContext/ReducerUserContext';
import UserRegister from './Components/UserRegister/UserRegister';
import EmployeePage from './Components/Employee/EmployeePage';
import EmployeeCreateTicket from './Components/Employee/EmployeeCreateTicket';
import EmployeeViewTicket from './Components/Employee/EmployeeViewTicket';
import FinanceManagerPage from './Components/FinanceManager/FinanceManagerPage';
import ManagerViewTicket from './Components/FinanceManager/ManagerViewTicket';

function App() {
  return (
    <div className="App">
      {/* <UserProvider> */}
      <AuthProvider>
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace />}/>
            <Route path="/login" element={<UserManagement/>}></Route>
            <Route path="/register" element={<UserRegister/>}></Route>
            <Route path="/employee" element={<EmployeePage/>}></Route>
            <Route path="/employee/ticket" element={<EmployeeCreateTicket/>}></Route>
            <Route path="/employee/ticket/view" element={<EmployeeViewTicket/>}></Route>
            <Route path="/manager" element={<FinanceManagerPage/>}></Route>
            <Route path="/manager/ticket" element={<ManagerViewTicket/>}></Route>
        </Routes>
      </AuthProvider>
      {/* </UserProvider> */}
      
      
    </div>
  );
}

export default App;
