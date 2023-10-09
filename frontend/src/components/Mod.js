import React from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

function Mod ({ children, open, close, title }) {
  if (!open) {
    return null;
  }
  return (
    <div
      className="modal show"
      style={{
        display: 'block',
      }}
    >
      <Modal.Dialog>
        <Modal.Header>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <p>{children}</p>
        </Modal.Body>

        <Modal.Footer>
          <Button name="mod" variant="secondary" onClick={close}>Close</Button>
        </Modal.Footer>
      </Modal.Dialog>
    </div>
  )
}

export default Mod;
