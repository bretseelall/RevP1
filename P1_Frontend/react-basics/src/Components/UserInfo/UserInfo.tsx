import React, { useContext } from 'react'
import { UserContext } from '../UserContext/UserContext'
import { AuthContext } from '../UserContext/ReducerUserContext';
import { Link, useNavigate } from 'react-router-dom';

function UserInfo() {
    //const context = useContext(UserContext);
    const context = useContext(AuthContext);

    if(!context){
        throw new Error("UserInfo must be used within a user provider");
    }
    // const {user, logout} = context;
    const {state, dispatch} = context;
  return (
    <>
        {
            state.user && (
                <div>
                    <p>Welcome, {state.user.username}</p>
                    <Link to="/login">
                        <button onClick={() => dispatch({type: 'LOGOUT'})} >Logout</button>
                    </Link>
                </div>
            )
        }
    </>
  )
}

export default UserInfo