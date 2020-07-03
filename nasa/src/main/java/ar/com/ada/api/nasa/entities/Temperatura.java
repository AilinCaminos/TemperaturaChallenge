package ar.com.ada.api.nasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "temperatura")
public class Temperatura {
@Id
@Column(name = "temperatura_id")
private Integer id;
@ManyToOne
@JoinColumn(name = "codigo_pais", referencedColumnName = "codigo_pais")
private Pais pais;
@Column(name = "año")
private Integer anio;
@Column(name = "temperatura")
private double temperatura;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public Pais getPais() {
    return pais;
}

public void setPais(Pais pais) {
    this.pais = pais;
    this.pais.getTemperaturas().add(this);
}

public Integer getAnio() {
    return anio;
}

public void setAnio(Integer anio) {
    this.anio = anio;
}

public double getTemperatura() {
    return temperatura;
}

public void setTemperatura(double temperatura) {
    this.temperatura = temperatura;
}
    
}