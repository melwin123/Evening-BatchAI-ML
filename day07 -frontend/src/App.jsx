import { Routes, Route, Navigate } from 'react-router-dom'
import Layout from './components/Layout'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Customers from './pages/Customers'
import ProtectedRoute from './auth/ProtectedRoute'

export default function App() {
  return (

    <Routes>
      <Route path="/login" element={<Login />} />

      {/* Protected layout routes */}
      <Route element={<ProtectedRoute><Layout /></ProtectedRoute>}>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/customers" element={<Customers />} />
      </Route>

      <Route path="*" element={<Navigate to="/login" />} />
    </Routes>

  )
}