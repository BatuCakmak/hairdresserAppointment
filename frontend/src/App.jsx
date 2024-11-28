import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import PageContainer from './container/PageContainer'
import LoginPage from './pages/LoginPage'
import RouterConfig from './config/RouterConfig'
import { data } from 'react-router'

function App() {

  const [message, setMessage] = useState("");

  return (
    <div>
      <PageContainer>
        <RouterConfig />
      </PageContainer>
    </div>
  )
}

export default App
