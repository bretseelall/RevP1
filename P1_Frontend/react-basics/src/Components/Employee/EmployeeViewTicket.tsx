import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../UserContext/ReducerUserContext';


type Ticket={
    ticketId: number,
    username: string,
    amount: string,
    description: string,
    status: any
}
function EmployeeViewTicket() {

    const context = useContext(AuthContext);
    if(!context){
        throw new Error("Login must be used within a UserProvider")
    }

    const [data, setData] = useState<Array<Ticket>>([]);
    const username = context.state.user?.username;

    useEffect(() => {
        fetch(`http://localhost:8080/ticket/employee/${username}`).then(response => response.json()).then(data =>{
            setData(data)
            //console.log(data);
        }).catch(error => console.error('Error grabbing tickets: ', error))
        
    }, [])
  return (
    <>
        <h1 style={{backgroundColor: 'cyan'}}>My Tickets</h1>
        <table style={{borderRadius: 5}}>
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {data.map(item => (
                    <tr key={item.ticketId}>
                        <td>{'$'}{item.amount}</td>
                        <td>{item.description}</td>
                        <td>{item.status}</td>
                    </tr>
                ))}
            </tbody>
            
        </table>
    </>
  )
}

export default EmployeeViewTicket