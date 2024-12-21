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
    <>
      <h1 style={{backgroundColor: 'cyan'}}>Login</h1>
      <form style={{display: 'flex', justifyContent: 'center'}} onSubmit={handleSubmit}>

        <label>Username:
            <input style={{boxSizing: 'border-box', margin: 8}} type="text" value={username} onChange={(e:any)=> setUsername(e.target.value)}></input>
        </label><br/>

        <label>Password:
            <input style={{boxSizing: 'border-box', margin: 8}} type='password' value={password} onChange={(e:any)=> setPassword(e.target.value)}></input>
        </label><br/>

        <button type='submit'>Submit</button>
        <button type='button' onClick={handleRegister}>Create Account</button>
      </form>
    </>
    
  )
}

export default UserLogin