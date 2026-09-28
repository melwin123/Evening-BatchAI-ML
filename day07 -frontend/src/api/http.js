import axios from 'axios'

const http = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/api',
    headers: { 'Content-Type': 'application/json' },
    timeout: 10000
})

// attach the token to every outgoing request
http.interceptors.request.use((config) => {
    const raw = sessionStorage.getItem('auth')
    if (raw) {
        const { token } = JSON.parse(raw)
        if (token) config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

// turn every backend failure into one predictable object
http.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response) {
            const { status, data } = error.response
            if (status === 401 && !error.config.url.includes('/auth/login')) {
                sessionStorage.removeItem('auth')
                window.location.href = '/login'
            }
            return Promise.reject({
                status,
                message: data?.message || `Request failed (${status})`,
                fields: data?.errors && Object.keys(data.errors).length ? data.errors : null
            })
        }
        if (error.request) {
            return Promise.reject({ message: 'Server not reachable. Is Spring Boot running on 8080?' })
        }
        return Promise.reject({ message: error.message })
    }
)

export default http
