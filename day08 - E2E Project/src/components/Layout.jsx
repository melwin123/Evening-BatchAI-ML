import { NavLink, Outlet, useNavigate } from 'react-router-dom'
import { useAuth } from '../auth/AuthContext.jsx'

export default function Layout() {
  const { auth, logout } = useAuth()
  const navigate = useNavigate()

  const signOut = () => {
    logout()
    navigate('/login')
  }

  return (
    <div className="shell">
      <header className="topbar">
        <div className="brand">AMC Bank</div>
        <div style={{ display: 'flex', alignItems: 'center', gap: 12 }}>
          <span className="muted">{auth.username} · {auth.roles.join(', ')}</span>
          <button className="ghost" style={{ height: 36 }} onClick={signOut}>Logout</button>
        </div>
      </header>

      <div className="body-row">
        <nav className="sidebar">
          <NavLink to="/dashboard">Dashboard</NavLink>
          <NavLink to="/customers">Customers</NavLink>
        </nav>
        <main><Outlet /></main>
      </div>
    </div>
  )
}
