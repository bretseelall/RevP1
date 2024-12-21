import React, { FormEvent, useState } from 'react'
import { useNavigate } from 'react-router-dom';

function UserRegister() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    function registerSubmit(event: FormEvent){
        event.preventDefault();
        fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({username, password}),
        }).then(response => response.json()).then(data => {

            console.log('User Registered as ', data)
        }).catch((error) => {
            alert("Error!");
            console.error('Error registering user: ', error)});
        
        navigate('/login');
        //console.log(username, password);
    }
  return (
    <>
        <h1 style={{backgroundColor: 'cyan'}}>Create Account</h1>
        <form style={{display: 'flex', justifyContent: 'center'}} onSubmit={registerSubmit}>
            <label>Username:
                <input style={{boxSizing: 'border-box', margin: 8}} type="text" value={username} onChange={(e:any)=> setUsername(e.target.value)}></input>
            </label><br/>
            
            <label>Password:
                <input style={{boxSizing: 'border-box', margin: 8}} type='password' value={password} onChange={(e:any)=> setPassword(e.target.value)}></input>
            </label><br/>
            
            <button type='submit'>Register</button>
        </form>
    </>
  )
}

export default UserRegister