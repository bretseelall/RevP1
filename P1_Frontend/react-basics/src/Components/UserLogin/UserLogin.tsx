import React, { FormEvent, useState } from 'react'

type UserInputProps ={
    username: string,
    setUsername: React.Dispatch<React.SetStateAction<string>>,
    password: string,
    setPassword: React.Dispatch<React.SetStateAction<string>>,
    handleSubmit:any,
    handleRegister: any
}

function UserLogin({username, setUsername, password, setPassword, handleSubmit, handleRegister}: UserInputProps) {

  return (
    <form onSubmit={handleSubmit}>

        <label>Username:
            <input type="text" value={username} onChange={(e:any)=> setUsername(e.target.value)}></input>
        </label><br/>
        
        <label>Password:
            <input type='password' value={password} onChange={(e:any)=> setPassword(e.target.value)}></input>
        </label><br/>
        
        <button type='submit'>Submit</button>
        <button type='button' onClick={handleRegister}>Create Account</button>
    </form>
  )
}

export default UserLogin