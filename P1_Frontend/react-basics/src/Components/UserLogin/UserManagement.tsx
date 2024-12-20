import React, { FormEvent, useContext, useEffect, useState } from 'react'
import UserLogin from './UserLogin'
import { UserContext } from '../UserContext/UserContext';
import UserInfo from '../UserInfo/UserInfo';
import { AuthContext } from '../UserContext/ReducerUserContext';
import { useNavigate } from 'react-router-dom';

function UserManagement() {
    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");

    // const context = useContext(UserContext);
    const context = useContext(AuthContext);
    if(!context){
        throw new Error("Login must be used within a UserProvider")
    }
    const {dispatch} = context;

    const navigate = useNavigate();

    function handleSubmit(event: FormEvent){
        event.preventDefault();
        let retUsername;
        let retPassword;
        const fetchData = async () =>{
            try{
                const response = await fetch('http://localhost:8080/login', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({username, password}),
                })
                const data = await response.json();
                if(data && data.manager === false){
                    dispatch({type: 'LOGIN', payload: {username, password}})
                    navigate("/employee");
                }
                else if(data && data.manager === true)
                {
                    dispatch({type: 'LOGIN', payload: {username, password}})
                    navigate("/manager");
                }
                    

                //console.log("Here is the response: ", await response.json());
            }catch (error){
                console.error("Wrong Credentials");
            }
        };

        fetchData();
        //console.log(fetchData);
        // const response = fetch('http://localhost:8080/login', {
            // method: 'POST',
            // headers: {
            //     'Content-Type': 'application/json',
            // },
            // body: JSON.stringify({username, password}),
        // }).then(response => response.json()).then(data => {
        //     console.log('User Logged in ', data)
        //     retUsername = data.username;
        //     retPassword = data.password;
        //     dispatch({type: 'LOGIN', payload: {username, password}})
        //     if(username === retUsername && retPassword === password){
        //         return;
        //     }
        // }).catch(error => console.error('Error creating user: ', error));

        // console.log("Here is the username: ", retUsername);

        // API Call to verify user credentials
        
        //console.log(username, password);


        // dispatch({type: 'LOGIN' /*API call here?*/, payload: {username, password}})
        // dispatch({type: 'LOGOUT'})

        // if(context){
        //     context?.login(username,password);
        // }
        
    }

    function handleRegister(event: FormEvent){
        event.preventDefault();
        navigate('/register');
        console.log("We have landed");
    }
  return(
    <>
        <UserLogin username={username} setUsername={setUsername} password={password} setPassword={setPassword} handleSubmit={handleSubmit} handleRegister={handleRegister}/>
        {/* <UserInfo/> */}
    </>
  )
}

export default UserManagement