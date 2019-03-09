package br.edu.iftm.sistemaClientes.controller;

import br.edu.iftm.sistemaClientes.model.dao.ClienteDAO;
import br.edu.iftm.sistemaClientes.model.domain.Cliente;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

public final class ClienteController {
    
    private final PropertyChangeSupport propertyChangeSupport = 
            new PropertyChangeSupport(this);
    
    private Cliente clienteDigitado;
    
    private Cliente clienteSelecionado;
    
    private List<Cliente> clientesTabela;
    
    private final ClienteDAO clienteDAO;
    
    public ClienteController() {
        clienteDAO = new ClienteDAO();
        clientesTabela = ObservableCollections.observableList(new ArrayList<Cliente>());
        novo();
        pesquisar();
    }

    public Cliente getClienteDigitado() {
        return clienteDigitado;
    }

    public void setClienteDigitado(Cliente clienteDigitado) {
        Cliente clienteDigitadoOld = this.clienteDigitado;
        this.clienteDigitado = clienteDigitado;
        propertyChangeSupport.firePropertyChange("clienteDigitado",
                clienteDigitadoOld, clienteDigitado);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
        
        if(this.clienteSelecionado != null) {
            setClienteDigitado(clienteSelecionado);
        }
    }

    public List<Cliente> getClientesTabela() {
        return clientesTabela;
    }

    public void setClientesTabela(List<Cliente> clientesTabela) {
        this.clientesTabela = clientesTabela;
    }

    public void novo() {
        setClienteDigitado(new Cliente());
    }

    public void pesquisar() {
        clientesTabela.clear();
        clientesTabela.addAll(clienteDAO.pesquisar(clienteDigitado));
    }
    
    public void salvar() {
        clienteDAO.salvarAtualizar(clienteDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir() {
        clienteDAO.excluir(clienteDigitado);
        novo();
        pesquisar();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
}
