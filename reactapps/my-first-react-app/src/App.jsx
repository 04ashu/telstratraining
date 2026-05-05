import { useState } from 'react'
import './App.css'
import UserList from './UserList'
import Button from 'react-bootstrap/Button';

function App() {
  const users = [
    {id: 1, name: "Virat"},
    {id: 2, name: "Rahul"},
    {id: 3, name: "Rohit" }
  ];

  const users2 = [
    {id: 1, name: "Dravid"},
    {id: 2, name: "Sachin"},
    {id: 3, name: "Yuvraj" }
  ];  
  const [count, setCount] = useState(0)

  return (
    <>
      <section id="center">
        <div>
          <h1>React Demo App</h1>
        </div>
        <button
          type="button"
          className="counter"
          onClick={() => setCount((count) => count + 1)}
        >
          Count is {count}
        </button>
        
        {/* <h2>First Players List</h2> */}
        <Button variant = "success">First Players List</Button>
        <UserList users={users}/>

        {/* <h2>Second Players List</h2> */}
        <Button variant = "primary">Second Players List</Button>
        <UserList users={users2}/>
      </section>
    </>
  )
}

export default App
