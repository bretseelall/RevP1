import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../UserContext/ReducerUserContext'
import UserInfo from '../UserInfo/UserInfo';
import { Link } from 'react-router-dom';



function EmployeePage() {
    const context = useContext(AuthContext);
    if(!context){
        throw new Error("Login must be used within a UserProvider")
    }
    

    //console.log(context.state.user?.username);

    // const {dispatch} = context;

  return (
    <>
        <UserInfo/>
        <Link to="/employee/ticket">
            <button>Create Ticket</button>
        </Link>

        <Link to="/employee/ticket/view">
            <button>View Tickets</button>
        </Link>
        
    </>
  )
}

export default EmployeePage