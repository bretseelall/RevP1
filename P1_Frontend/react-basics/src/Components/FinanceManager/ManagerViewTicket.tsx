import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../UserContext/ReducerUserContext';

type Ticket={
    ticketId: number,
    username: string,
    amount: string,
    description: string,
    status: any
}

function ManagerViewTicket() {
    const context = useContext(AuthContext);
    if(!context){
        throw new Error("Login must be used within a UserProvider")
    }

    const [data, setData] = useState<Array<Ticket>>([]);
    //const username = context.state.user?.username;
    const fetchTickets = () =>{
        fetch('http://localhost:8080/ticket/manager/PENDING').then(response => response.json()).then(data =>{
            setData(data)
            //console.log(data);
        }).catch(error => console.error('Error grabbing tickets: ', error))
    }

    useEffect(() => {
        fetchTickets();
    }, [])
    function onApprove(event: any){
        const status = 'APPROVED'
        console.log('Approved', event.target.value)
        fetch(`http://localhost:8080/ticket/manager/update/${event.target.value}`,{
            method: 'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({status}),
        }).then(response => response.json()).then(() => {
            fetchTickets();
        }).catch(error => console.error('Error updating ticket: ', error));
    }
    function onDeny(event: any){
        const status = 'DENIED'
        console.log('Denied', event.target.value)
        fetch(`http://localhost:8080/ticket/manager/update/${event.target.value}`,{
            method: 'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({status}),
        }).then(response => response.json()).then( () =>{
            fetchTickets();
        }).catch(error => console.error('Error updating ticket: ', error));
    }

  return (
    <>
        <h1 style={{backgroundColor: 'cyan'}}>Pending Tickets</h1>
        <table>
            <thead>
                <tr>
                    <th>Ticket ID</th>
                    <th>User</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {data.map(item => (
                    <tr key={item.ticketId}>
                        <td>{item.ticketId}</td>
                        <td>{item.username}</td>
                        <td>{'$'}{item.amount}</td>
                        <td>{item.description}</td>
                        <td>{item.status}</td>
                        <td><button onClick={onApprove} value={item.ticketId}>Approve</button>
                        <button onClick={onDeny} value={item.ticketId}>Deny</button></td>
                    </tr>
                ))}
            </tbody>
            
        </table>
    </>
  )
}

export default ManagerViewTicket