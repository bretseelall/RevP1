import React, { useContext } from 'react'
import UserInfo from '../UserInfo/UserInfo'
import { AuthContext } from '../UserContext/ReducerUserContext';
import { Link } from 'react-router-dom';

function FinanceManagerPage() {
    const context = useContext(AuthContext);
    if(!context){
        throw new Error("Login must be used within a UserProvider")
    }
  return (
    <>
        <UserInfo/>
        <br/>
        <Link to="/manager/ticket">
            <button>View Pending Tickets</button>
        </Link>
    </>
    
  )
}

export default FinanceManagerPage