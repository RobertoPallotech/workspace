package com.examen.examenpractico.model;

import java.util.Date;
import com.examen.examenpractico.model.HistorialCitas;
import com.examen.examenpractico.model.Medico;
import com.examen.examenpractico.model.Paciente;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CitasPacienteMedico")
public class CitasMedicoPaciente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	    private String descripcion;
	    private Date citaDia;
	    private String cedula;
	    private String direccion;
	    private String telefono;
	    private String estado;
	    
	    @ManyToOne(fetch = FetchType.LAZY,optional = false)
		@JoinColumn(name = "id_paciente")
		private Paciente paciente;
	    
	    @ManyToOne(fetch = FetchType.LAZY,optional = false)
		@JoinColumn(name = "id_medico")
		private Medico medico;
	    
	    @OneToOne(fetch = FetchType.LAZY,optional = false)
		@JoinColumn(name = "id_historialCita")
	    private HistorialCitas historialCitas;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Date getCitaDia() {
			return citaDia;
		}

		public void setCitaDia(Date citaDia) {
			this.citaDia = citaDia;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Medico getMedico() {
			return medico;
		}

		public void setMedico(Medico medico) {
			this.medico = medico;
		}

		public HistorialCitas getHistorialCitas() {
			return historialCitas;
		}

		public void setHistorialCitas(HistorialCitas historialCitas) {
			this.historialCitas = historialCitas;
		}

	    
}
