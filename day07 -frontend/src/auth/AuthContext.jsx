import { createContext, useContext, useState } from 'react'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
    const [auth, setAuth] = useState(() => {
        const raw = sessionStorage.getItem('auth')
        return raw ? JSON.parse(raw) : null
    })

    const login = (data) => {
        sessionStorage.setItem('auth', JSON.stringify(data))
        setAuth(data)
    }

    const logout = () => {
        sessionStorage.removeItem('auth')
        setAuth(null)
    }

    const hasRole = (role) => !!auth && auth.roles.includes(role)

    return (
        <AuthContext.Provider value={{ auth, login, logout, hasRole }}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => useContext(AuthContext)
