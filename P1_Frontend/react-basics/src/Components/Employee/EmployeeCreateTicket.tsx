import React, { FormEvent, useContext, useState } from 'react'
import { AuthContext } from '../UserContext/ReducerUserContext';
import { useNavigate } from 'react-router-dom';

function EmployeeCreateTicket() {
    const context = useContext(AuthContext);
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");

    const username = context?.state.user?.username;
    console.log("Here is username", username);

    const navigate = useNavigate();

    function createTicket(event: FormEvent){
        event.preventDefault();
        fetch('http://localhost:8080/ticket/employee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({username, amount, description}),
        }).then(response => response.json()).then(data => {

            console.log('Ticket created: ', data)
        }).catch(error => console.error('Error creating ticket: ', error));
        console.log("Hit");
        navigate('/employee');
    }
  return (
    <form onSubmit={createTicket}>
        <label>Amount:
            <input type="text" value={amount} onChange={(e: any) => setAmount(e.target.value)}></input>
        </label><br/>

        <label>Description:
            <input type="text" value={description} onChange={(e: any) => setDescription(e.target.value)}></input>
        </label><br/>

        <button type='submit'>Submit Ticket</button>

    </form>
//     <form onSubmit={registerSubmit}>
//     <label>Username:
//        <input type="text" value={username} onChange={(e:any)=> setUsername(e.target.value)}></input>
//    </label><br/>
   
//    <label>Password:
//        <input type='password' value={password} onChange={(e:any)=> setPassword(e.target.value)}></input>
//    </label><br/>
   
//    <button type='submit'>Register</button>
// </form>
  )
}

export default EmployeeCreateTicket