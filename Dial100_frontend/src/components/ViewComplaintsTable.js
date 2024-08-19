import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/ViewComplaintsTable.css';

function ViewComplaintsTable() {
    const navigate = useNavigate();
    const [complaints, setComplaints] = useState([]);  // Initialize as an empty array
    const userId = JSON.parse(localStorage.getItem('user')).id;  // Assuming the user object contains the 'id' field

    useEffect(() => {
        const userData = JSON.parse(localStorage.getItem('user'));
        // Fetch complaints data from the backend
        fetch(`http://localhost:8080/api/complaints/user/${userData.userId}`)
            .then(response => response.json())
            .then(data => {
                if (Array.isArray(data)) {  // Ensure that the data is an array
                    setComplaints(data);
                } else {
                    console.error('Error: Data is not an array', data);
                    setComplaints([]);  // Set an empty array if the data is not an array
                }
            })
            .catch(error => console.error('Error fetching complaints:', error));
    }, [userId]);

    // Function to handle logout
    const handleLogout = () => {
        localStorage.removeItem('user');
        navigate('/');
    };

    // Function to navigate to complaint details page
    const handleViewDetails = (complaintId) => {
        navigate(`/plaintiff-complaint-details/${complaintId}`);
    };

    // Function to navigate to updates page
    const handleViewUpdates = (complaintId) => {
        navigate(`/plaintiff-update-details/${complaintId}`);
    };

    return (
        <div className="dashboard">
            <div className="side-nav">
                <div className="logo">Dial100</div>
                <ul>
                    <li><a href="/register-complaint">Register Complaint</a></li>
                    <li><a href="/view-complaints" className="active">View Complaints</a></li>
                </ul>
            </div>

            <div className="content-area">
                <div className="top-nav">
                    <input type="text" className="search-bar" placeholder="Search here..." />
                    <div className="profile-options">
                        <a href="/profile">Your Profile</a>
                        <a href="/" onClick={handleLogout}>Logout</a>
                    </div>
                </div>

                <div className="main-content">
                    <h1>All Complaints</h1>
                    <table className="complaints-table">
                        <thead>
                            <tr>
                                <th>Complaint ID</th>
                                <th>Details</th>
                                <th>Updates</th>
                            </tr>
                        </thead>
                        <tbody>
                            {complaints.length > 0 ? (
                                complaints.map((complaint) => (
                                    <tr key={complaint.complaintId}>
                                        <td>{complaint.complaintId}</td>
                                        <td>
                                            <button onClick={() => handleViewDetails(complaint.complaintId)} className="view-button">View</button>
                                        </td>
                                        <td>
                                            <button onClick={() => handleViewUpdates(complaint.complaintId)} className="view-button">View</button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="3">No complaints found.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default ViewComplaintsTable;
