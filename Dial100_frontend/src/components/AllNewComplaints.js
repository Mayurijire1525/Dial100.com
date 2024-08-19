import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/AllNewComplaints.css';

function AllNewComplaints() {
  const navigate = useNavigate();
  const [complaints, setComplaints] = useState([]);

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user'); // Clear localStorage
    navigate('/'); // Redirect to home page
  };

  // Fetch complaints with status "new"
  useEffect(() => {
    fetch('http://localhost:8080/api/complaints/new') // Fetch complaints with status "new"
      .then(response => response.json())
      .then(data => setComplaints(data))
      .catch(error => console.error('Error fetching complaints:', error));
  }, []);

  // Function to handle the view button click
  const handleViewClick = (complaintId) => {
    navigate(`/authority-complaint-details/${complaintId}`); // Redirect to details page
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All New Complaints</a></li>
          <li><a href="/my-investigations">My Investigations</a></li>
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
          <h1>All New Complaints</h1>
          <table className="complaints-table">
            <thead>
              <tr>
                <th>Complaint ID</th>
                <th>Details</th>
              </tr>
            </thead>
            <tbody>
              {complaints.length > 0 ? complaints.map(complaint => (
                <tr key={complaint.complaintId}>
                  <td>{complaint.complaintId}</td>
                  <td>
                    <button className="view-button" onClick={() => handleViewClick(complaint.complaintId)}>View</button>
                  </td>
                </tr>
              )) : (
                <tr>
                  <td colSpan="2" className="no-data">No new complaints</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default AllNewComplaints;
